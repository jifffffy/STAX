/*****************************************************************************/
/* Software Testing Automation Framework (STAF)                              */
/* (C) Copyright IBM Corp. 2002                                              */
/*                                                                           */
/* This software is licensed under the Eclipse Public License (EPL) V1.0.    */
/*****************************************************************************/

package io.jiffy.stax.plugin.pe;

import com.ibm.staf.service.stax.*;
import org.pf4j.Extension;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class DelayActionFactory extends ActionFactoryExtensionPointAdapter implements STAXJobManagementHandler {
    public static final String EXT_DELAY = "delay";
    private static Map fParameterMap = new HashMap();

    public DelayActionFactory() {
        super(EXT_DELAY);
    }


    public void initialize(STAX staxService) {
        // Note that in order for it's initJob method to be run at the start
        // of a job, you must register the factory as a Job Management handler.

        staxService.registerJobManagementHandler(this);
    }

    public String getParameter(String name) {
        return (String) fParameterMap.get(name);
    }


    public STAXAction parseAction(STAX staxService, STAXJob job, Node root) throws STAXException {
        DelayAction delay = new DelayAction();

        delay.setActionFactory(this);
        delay.setLineNumber(root);
        delay.setXmlFile(job.getXmlFile());
        delay.setXmlMachine(job.getXmlMachine());

        NodeList children = root.getChildNodes();

        for (int i = 0; i < children.getLength(); ++i) {
            Node thisChild = children.item(i);

            if (thisChild.getNodeType() == Node.COMMENT_NODE) {
                /* Do nothing */
            } else if (thisChild.getNodeType() == Node.CDATA_SECTION_NODE) {
                /* Do nothing */
            } else if (thisChild.getNodeType() == Node.TEXT_NODE) {
                delay.setElementInfo(new STAXElementInfo(root.getNodeName()));

                delay.setDelayValue(STAXUtil.parseAndCompileForPython(thisChild.getNodeValue(), delay));
            }
        }

        return delay;
    }

    // STAXJobManagement methods

    public void initJob(STAXJob job) {
        // Create a default signal handler for signal ExtDelayInvalidValue
        // that logs and displays a message and terminates the job.

        ArrayList signalHandlerActionList = new ArrayList();

        // Note the message is in a Python form so it can be evaluated.
        String msg = "'ExtDelayInvalidValue signal raised.  " +
                "Terminating job. %s' % ExtDelayInvalidValueMsg";

        signalHandlerActionList.add(new STAXMessageAction(msg));
        signalHandlerActionList.add(new STAXLogAction(msg, "'error'", STAXJob.JOB_LOG));
        signalHandlerActionList.add(new STAXTerminateAction("'main'"));

        // Add the default signal handler to the job's default action list.
        job.addDefaultAction(new STAXSignalHandlerAction("'ExtDelayInvalidValue'", new STAXSequenceAction(signalHandlerActionList)));

        // Create a map that contains a key named "extDelayNum" whose
        // value is initialized to 0.  This number is used in creating a
        // unique name for each delay element within a job.

        HashMap extDelayMap = new HashMap();
        extDelayMap.put("extDelayNum", new Integer(0));

        boolean result = job.setData("ext-delay-map", extDelayMap);
    }

    public void terminateJob(STAXJob job) { /* Do Nothing */ }
}
