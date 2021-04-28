package io.jiffy.stax.plugin.pe.action;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.pe.Constants;
import org.pmw.tinylog.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class BeepAction extends STAXActionDefaultImpl {
    private int hz;
    private int msecs;
    private double vol;

    @Override
    public void execute(STAXThread thread) {
        try {
            float sampleRate = 8000f;
            byte[] buf = new byte[1];
            AudioFormat af = new AudioFormat(
                    sampleRate, // sampleRate
                    8,           // sampleSizeInBits
                    1,           // channels
                    true,        // signed
                    false);      // bigEndian
            SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af);
            sdl.start();
            for (int i = 0; i < msecs * 8; i++) {
                double angle = i / (sampleRate / hz) * 2.0 * Math.PI;
                buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
                sdl.write(buf, 0, 1);
            }
            sdl.drain();
            sdl.stop();
            sdl.close();

            thread.popAction();
            return;
        } catch (Exception e) {
            Logger.error(e);
            setElementInfo(new STAXElementInfo(getElement()));
            thread.popAction();
            thread.pySetVar(Constants.RC, -1);
            thread.setSignalMsgVar(Constants.BEEP_ERROR, STAXUtil.formatErrorMessage(this));
            thread.raiseSignal(Constants.BEEP_ERROR);
        }
    }

    @Override
    public STAXAction cloneAction() {
        BeepAction clone = new BeepAction();

        clone.setElement(getElement());
        clone.setLineNumberMap(getLineNumberMap());
        clone.setXmlFile(getXmlFile());
        clone.setXmlMachine(getXmlMachine());

        clone.setHz(getHz());
        clone.setMsecs(getMsecs());
        clone.setVol(getVol());
        return clone;
    }

    public int getHz() {
        return hz;
    }

    public void setHz(int hz) {
        this.hz = hz;
    }

    public int getMsecs() {
        return msecs;
    }

    public void setMsecs(int msecs) {
        this.msecs = msecs;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }
}
