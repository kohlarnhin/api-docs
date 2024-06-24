package com.koh.apidocs;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiClass;

public class ApiDocsGenerate extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 获取当前编辑器中的文件
        PsiFile psiFile = e.getData(com.intellij.openapi.actionSystem.CommonDataKeys.PSI_FILE);
        if (psiFile instanceof PsiJavaFile javaFile) {
            String apiTag = ApiTagReader.getApiTag(javaFile);
            Messages.showInfoMessage(apiTag, "提示");
        } else {
            Messages.showInfoMessage("文件非java类型文件", "错误");
        }
    }
}
