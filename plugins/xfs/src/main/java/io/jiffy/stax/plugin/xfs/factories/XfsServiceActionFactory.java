package io.jiffy.stax.plugin.xfs.factories;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.xfs.actions.XfsServiceAction;
import org.pmw.tinylog.Logger;
import org.w3c.dom.Node;


public abstract class XfsServiceActionFactory<T extends XfsServiceAction> extends ActionFactorySupport<T> implements STAXJobManagementHandler {

    public XfsServiceActionFactory(String name, Class<T> clazz) {
        super(name, clazz);

    }

    @Override
    public void initialize(STAX stax) {
        stax.registerJobManagementHandler(this);
    }

    @Override
    public void initJob(STAXJob job) {
    }


    @Override
    public void terminateJob(STAXJob job) {
    }

    /**
     * XFS service(eg. ptr,cim,cdm,epp) 下面可以跟任何%task% elements
     * 代码逻辑参考STAXBlockActionFactory
     * @param action
     * @param child
     */
    @Override
    protected void handleChildNode(STAX staxService, STAXJob job, T action, Node root, Node child) throws STAXException {
        STAXAction serviceAction = action.getAction();
        if (serviceAction != null) {
            action.setElementInfo(
                    new STAXElementInfo(root.getNodeName(),
                            STAXElementInfo.NO_ATTRIBUTE_NAME,
                            STAXElementInfo.LAST_ELEMENT_INDEX,
                            child.getNodeName()));
            Logger.error("{} 只能包含一个要执行的action", action.getElement());
            throw new STAXInvalidXMLElementCountException(STAXUtil.formatErrorMessage(action), action);
        }

        STAXActionFactory factory = staxService.getActionFactory(child.getNodeName());

        if (factory == null) {
            action.setElementInfo(
                    new STAXElementInfo(
                            root.getNodeName(),
                            STAXElementInfo.NO_ATTRIBUTE_NAME,
                            STAXElementInfo.LAST_ELEMENT_INDEX,
                            "No action factory for element type \"" + child.getNodeName() + "\""));
            Logger.error("No action factory for element type {}", child.getNodeName());
            throw new STAXInvalidXMLElementException(STAXUtil.formatErrorMessage(action), action);
        }
        serviceAction = factory.parseAction(staxService, job, child);

        if(serviceAction == null) {
            Logger.error("无法解析 {}", child.getNodeName());
            throw new STAXInvalidXMLElementCountException(STAXUtil.formatErrorMessage(action), action);
        }

        action.setAction(serviceAction);
    }
}


