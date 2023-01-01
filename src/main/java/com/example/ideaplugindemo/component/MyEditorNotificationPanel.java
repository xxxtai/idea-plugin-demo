package com.example.ideaplugindemo.component;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;

import com.intellij.ui.EditorNotificationPanel;
import com.intellij.ui.EditorNotifications;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyEditorNotificationPanel extends EditorNotifications.Provider<EditorNotificationPanel> implements DumbAware {
    private static final Logger LOG = Logger.getInstance(MyEditorNotificationPanel.class);
    private static final Key<EditorNotificationPanel> KEY = Key.create("plugin.demo.notification.panel");

    @NotNull
    @Override
    public Key<EditorNotificationPanel> getKey() {
        return KEY;
    }

    @Nullable
    @Override
    public EditorNotificationPanel createNotificationPanel(@NotNull VirtualFile file, @NotNull FileEditor fileEditor, @NotNull Project project) {
        EditorNotificationPanel panel = new EditorNotificationPanel(fileEditor);
        panel.setText("测试测试测试测试");
        panel.createActionLabel("action", () -> {
            if (!project.isDisposed()) {
                file.refresh(false, false);
                EditorNotifications.getInstance(project).updateNotifications(file);
            }
        });
        return panel;
    }
}
