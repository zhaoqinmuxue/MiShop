package com.example.aoli_compiler;

import com.example.aoli_annotations.AppRegisterGenerator;
import com.example.aoli_annotations.EntryGenerator;
import com.example.aoli_annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

@AutoService(Processor.class)
public class AoliProcessor extends AbstractProcessor {

    //返回所有需要处理的注解的CanonicalName名称
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportAnnotations();
        for (Class<? extends Annotation> annotation : supportAnnotations){
            //相当于getName，对特殊类表示方法略有差异
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    //暂时不知道set的作用
    //roundEnvironment是读取结果，从中可以取出想要的信息
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterCode(roundEnvironment);
        return false;
    }

    //Annotation是注解的父类
    private Set<Class<? extends Annotation>> getSupportAnnotations(){
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        //EntryGenerator继承自Annotation
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }

    private void generateEntryCode(RoundEnvironment env){
        final EntryVisitor entryVisitor =
                new EntryVisitor(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVisitor);
    }

    private void generatePayEntryCode(RoundEnvironment env){
        final PayEntryVisitor payEntryVisitor =
                new PayEntryVisitor(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, payEntryVisitor);
    }

    private void generateAppRegisterCode(RoundEnvironment env) {
        final AppRegisterVisitor appRegisterVisitor =
                new AppRegisterVisitor(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, appRegisterVisitor);
    }

    private void scan(RoundEnvironment env, Class<? extends Annotation> annotation, AnnotationValueVisitor visitor){
        //得到所有被该annotation注解的元素
        for (Element typeElement : env.getElementsAnnotatedWith(annotation)){
            //得到该元素上所有注解的自身信息
            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
            for (AnnotationMirror annotationMirror : annotationMirrors){
                Element element = annotationMirror.getAnnotationType().asElement();
                if (((TypeElement)(annotationMirror.getAnnotationType().asElement())).getQualifiedName().toString().equals(annotation.getCanonicalName())) {
                    //得到该注解的<方法元素,值>
                    final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                            = annotationMirror.getElementValues();
                    //获得每个键值对的值，执行accept方法
                    for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues.entrySet()) {
                        entry.getValue().accept(visitor, null);
                    }
                }
            }
        }
    }
}
