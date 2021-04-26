package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.xfs.actions.execute.EjectSlotStartAction;
import io.jiffy.stax.plugin.xfs.actions.execute.OpenAction;
import io.jiffy.stax.plugin.xfs.factories.XfsCommandActionFactory;
import org.pf4j.Extension;
import org.w3c.dom.Node;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class EjectSlotStartActionFactory extends XfsCommandActionFactory<EjectSlotStartAction> {
    public EjectSlotStartActionFactory() {
        super("eject-slot-start", EjectSlotStartAction.class);
    }


    @Override
    protected void handleChildNode(STAX staxService, STAXJob job, EjectSlotStartAction action, Node root, Node child) throws STAXException {
        action.setElementInfo(new STAXElementInfo(root.getNodeName()));
        String value = STAXUtil.parseAndCompileForPython(child.getNodeValue(), action);
        switch (child.getNodeName()) {
            case "type":
                action.setType(value);
                break;
            case "cmdData":
                action.setCmdData(value);
                break;
            default:
                action.setElementInfo(
                        new STAXElementInfo(
                                root.getNodeName(),
                                STAXElementInfo.NO_ATTRIBUTE_NAME,
                                STAXElementInfo.LAST_ELEMENT_INDEX,
                                "必须包括type或cmdData元素: "));
                throw new STAXInvalidXMLNodeTypeException(STAXUtil.formatErrorMessage(action), action);

        }
    }
}
