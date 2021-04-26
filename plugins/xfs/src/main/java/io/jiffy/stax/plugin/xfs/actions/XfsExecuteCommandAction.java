package io.jiffy.stax.plugin.xfs.actions;

public abstract class XfsExecuteCommandAction extends XfsCommandAction {

    public abstract String getParameter();

    public abstract void setParameter(String param);

    public abstract String createCommand();

}