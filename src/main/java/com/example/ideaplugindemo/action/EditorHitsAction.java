package com.example.ideaplugindemo.action;

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.codeInsight.hint.HintManagerImpl;
import com.intellij.codeInsight.hint.HintUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.ui.LightweightHint;
import com.intellij.util.ui.accessibility.AccessibleContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class EditorHitsAction extends AnAction {
    private final static String TIPS = "<html><div>\n<div>\n  <span style=\"color: #777777; font-size: 1em;\">AutoFix:</span>&nbsp;&nbsp;" +
        "CONTENT\n</div>\n<label  style=\"color: #2470b3; font-size: 0.9em; \">TIPS</label>\n</div></html>";
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor == null) {
            return;
        }
        int offset = editor.getCaretModel().getOffset();
        int line = editor.getCaretModel().getVisualPosition().getLine() + 1;
        int column = editor.getCaretModel().getVisualPosition().getColumn() + 1;
        showHint(editor, "offset:" + offset + ", line:" + line + ", column:" + column);
//        showHint(editor, TIPS);
    }

    public void showHint(Editor editor, String text) {
        if (StringUtils.isBlank(text)) {
            return;
        }
        HintManagerImpl hintManager = (HintManagerImpl) HintManagerImpl.getInstance();
        JComponent label = HintUtil.createInformationLabel(text, null, null, null);
        AccessibleContextUtil.setName(label, "Hint");
        LightweightHint hint = new LightweightHint(label);
        Point p = HintManagerImpl.getHintPosition(hint, editor, editor.getCaretModel().getVisualPosition(), (short) 1);
        int flags = HintManager.HIDE_BY_ANY_KEY | HintManager.HIDE_BY_TEXT_CHANGE | HintManager.HIDE_BY_SCROLLING;
        hintManager.showEditorHint(hint, editor, p, flags, 0, true, (short) 1);
    }
}
