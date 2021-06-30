package io.jiffy.stax.plugin.pe.action;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.pe.Constants;
import io.jiffy.stax.plugin.pe.utils.ByteUtilities;
import org.pmw.tinylog.Logger;

/**
 * 串口action, eg: <com port='2' readTimeout='1000' writeTimeout='1000'>'0x0000fff'</com>
 */
public class ComAction extends STAXActionDefaultImpl {

    private int port = 1; // 默认读取com1
    private int readTimeout = 500; // 默认读取超时时间500ms
    private int writeTimeout = 500; // 默认写入超时时间500ms
    private String value;

    private STAXThread thread;
    private SerialPort serialPort;
    private String result;

    private State state = State.INIT;

    private final STAXHoldThreadCondition holdThreadCondition = new STAXHoldThreadCondition();

    enum State {
        INIT, WRITING, READING, COMPLETE
    }

    public ComAction() {
    }

    @Override
    public void execute(STAXThread thread) {

        this.thread = thread;

        switch (state) {
            case INIT:
                try {
                    // 打开指定com口
                    serialPort = SerialPort.getCommPort("COM" + getPort());
                    if (!serialPort.openPort()) {
                        handleException();
                        return;
                    }
                    // 设置模式 超时时间
                    serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, getReadTimeout(), getWriteTimeout());
                    // 添加写入监听器
                    registerForWrite();
                    // 添加有可读数据监听器
                    registerForRead();

                    state = State.WRITING;
                    break;

                } catch (Exception e) {
                    Logger.error(e);
                    handleException();
                }

            case WRITING:
                serialPort.writeBytes(ByteUtilities.hexStringToByteArray(getValue()), getValue().length());
                thread.addCondition(holdThreadCondition);
                break;
            case COMPLETE:
                closePort();
                thread.pySetVar(Constants.RC, 0);
                thread.pySetVar("STAFResult", getResult());
                thread.popAction();
                break;
        }
    }

    private void registerForRead() {
        serialPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                // 有数据可读时,检查事件是否是该监听器关注的事件
                if (state == State.READING && event.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    byte[] newData = new byte[serialPort.bytesAvailable()];
                    int numRead = serialPort.readBytes(newData, newData.length);
                    String result = ByteUtilities.bytesToHex(newData);
                    Logger.info("read data {} from com", result);
                    synchronized (this) {
                        setResult(result);
                        state = State.COMPLETE;
                        thread.schedule();
                    }
                }
            }
        });
    }

    private void registerForWrite() {
        serialPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_WRITTEN;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                // 数据写入成功后,改变状态
                if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_WRITTEN) {
                    state = State.READING;
                }
            }
        });
    }

    private void handleException() {
        setElementInfo(new STAXElementInfo(getElement()));
        thread.popAction();
        thread.pySetVar(Constants.RC, -1);
        closePort();
    }

    private void closePort() {
        if(serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
        }
    }

    @Override
    public STAXAction cloneAction() {
        ComAction clone = new ComAction();

        clone.setElement(getElement());
        clone.setLineNumberMap(getLineNumberMap());
        clone.setXmlFile(getXmlFile());
        clone.setXmlMachine(getXmlMachine());

        clone.setPort(getPort());
        clone.setValue(getValue());

        return clone;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
