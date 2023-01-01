package com.example.ideaplugindemo.action;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationAction;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;

public class BalloonNotificationAction extends AnAction {
    private static final Logger LOG = Logger.getInstance(BalloonNotificationAction.class);

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        NotificationGroupManager.getInstance().getNotificationGroup("Idea Plugin Demo")
            .createNotification("Idea Plugin Demo title","通知内容，点击动作之后会打一条错误日志", NotificationType.INFORMATION)
            .addAction(new NotificationAction("动作名称"){
                @Override
                public void actionPerformed(@NotNull AnActionEvent e, @NotNull Notification notification) {
                    LOG.error("BalloonNotificationAction NotificationAction actionPerformed");
                }
            })
            .notify(e.getProject());
    }
}
