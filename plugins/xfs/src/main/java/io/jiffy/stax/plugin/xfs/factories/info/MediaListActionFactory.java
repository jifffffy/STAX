package io.jiffy.stax.plugin.xfs.factories.info;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.info.MediaListAction;
import io.jiffy.stax.plugin.xfs.factories.XfsCommandActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class MediaListActionFactory extends XfsCommandActionFactory<MediaListAction> {

    public MediaListActionFactory() {
        super("media-list", MediaListAction.class);
    }
}
