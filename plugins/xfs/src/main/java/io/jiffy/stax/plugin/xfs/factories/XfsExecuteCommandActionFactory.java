package io.jiffy.stax.plugin.xfs.factories;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandAction;
import org.pmw.tinylog.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public abstract class XfsExecuteCommandActionFactory<T extends XfsExecuteCommandAction> extends XfsCommandActionFactory<T>{

    public XfsExecuteCommandActionFactory(String name, Class<T> clazz) {
        super(name, clazz);
    }

    @Override
    protected void handleTextNode(STAX staxService, STAXJob job, T action, Node root, Node child) throws STAXException {
        action.setElementInfo(new STAXElementInfo(root.getNodeName()));
        action.setParameter(STAXUtil.parseAndCompileForPython(child.getNodeValue(), action));
    }

    @Override
    protected void handleRootAttributes(STAX staxService, STAXJob job, T action, Node root, NamedNodeMap attrs) throws STAXException {
        Node eventsNode = attrs.getNamedItem("events");
        // 处理元素events属性, 如 <read-raw-data events="['INSERTED', 'COMPLETE']"></read-raw-data>
        if(eventsNode != null) {
            String events = STAXUtil.parseAndCompileForPython(eventsNode.getNodeValue(), action);
            Logger.info("get element {} attr = {}", root.getNodeName(), events);
            action.setEvents(events);
        }
    }
}
