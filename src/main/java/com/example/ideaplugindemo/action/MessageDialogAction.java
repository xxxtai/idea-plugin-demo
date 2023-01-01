package com.example.ideaplugindemo.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class MessageDialogAction extends AnAction {
    private static final Logger LOG = Logger.getInstance(BalloonNotificationAction.class);
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
//        Messages.showMessageDialog(e.getProject(), "通知测试，测试测试测试", "Plugin Demo", Messages.getErrorIcon());

        int result = Messages.showYesNoCancelDialog(e.getProject(), "Yes or no dialog 测试 测试", "Plugin Demo",
            "Yes", "No", "Cancel", Messages.getQuestionIcon());
        if (result == Messages.YES) {
            LOG.error("MessageDialogAction actionPerformed YES");
        } else if (result == Messages.NO) {
            LOG.error("MessageDialogAction actionPerformed NO");
        } else if (result == Messages.CANCEL) {
            LOG.error("MessageDialogAction actionPerformed CANCEL");
        }

//        String content = Messages.showMultilineInputDialog(e.getProject(), "通知内容", "通知标题",
//            "", null, null);
//        LOG.error("MessageDialogAction actionPerformed " + content);
    }
}
