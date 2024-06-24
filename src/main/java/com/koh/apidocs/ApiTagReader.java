package com.koh.apidocs;


import com.intellij.psi.*;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.util.PsiTreeUtil;
import io.swagger.annotations.Api;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取ApiTag
 * 这个将作为四级标题，表示接口的分类
 * 接口下面的将作为五级标题作为每个接口的名称
 * 这个ApiTag读取顺序 ApiTag的value、注释、controller名称
 */
public class ApiTagReader {


    public static String getApiTag(PsiJavaFile javaFile) {
        return getByApiTag(javaFile)
                .or(() -> getByClassComment(javaFile))
                .orElse(getByClassName(javaFile));
    }

    private static String getByClassName(PsiJavaFile javaFile) {
        // 获取类的名称
        return javaFile.getClasses()[0].getName();
    }

    /**
     * 从ApiTag注解中获取
     *
     * @return ApiTag注解中的tags
     */
    private static Optional<String> getByApiTag(PsiJavaFile javaFile) {
        // Get all classes in the file
        PsiClass[] classes = PsiTreeUtil.getChildrenOfType(javaFile, PsiClass.class);
        if (classes != null && classes.length > 0) {
            // Get the first class
            PsiClass psiClass = classes[0];
            // Get the Api annotation
            PsiAnnotation apiAnnotation = psiClass.getAnnotation("com.tedia.pro.base.model.annotation.Remark");
//            PsiAnnotation apiAnnotation = psiClass.getAnnotation(Api.class.getName());
            if (apiAnnotation != null) {
                // Get the tags attribute value
                PsiAnnotationMemberValue tagsValue = apiAnnotation.findAttributeValue("value");
                if (tagsValue != null) {
                    return Optional.of(tagsValue.getText());
                }
            }
        }
        return Optional.empty();
    }

    /**
     * 获取注释中@description的内容
     *
     * @param javaFile
     * @return
     */
    public static Optional<String> getByClassComment(PsiJavaFile javaFile) {
        String commentText = getClassCommentText(javaFile);
        // TODO 后期可配置
        // 创建正则表达式匹配 @description 后面的内容
        Pattern pattern = Pattern.compile("@description\\s*(.*)");
        Matcher matcher = pattern.matcher(commentText);
        if (matcher.find()) {
            // 获取 @description 后面的内容
            return Optional.of(matcher.group(1));
        }
        return Optional.empty();
    }

    /**
     * 获取注释文本
     *
     * @param javaFile 文件
     * @return 注释文本
     */
    private static String getClassCommentText(PsiJavaFile javaFile) {
        // 获取文件中的所有类
        PsiClass[] classes = PsiTreeUtil.getChildrenOfType(javaFile, PsiClass.class);
        if (classes != null && classes.length > 0) {
            // 获取第一个类
            PsiClass psiClass = classes[0];
            // 获取类的文档注释
            PsiDocComment docComment = psiClass.getDocComment();
            if (docComment != null) {
                // 获取注释的文本
                return docComment.getText();
            }
        }
        return "";
    }
}
