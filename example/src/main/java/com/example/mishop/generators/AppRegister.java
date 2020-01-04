package com.example.mishop.generators;

import com.example.aoli_annotations.AppRegisterGenerator;
import com.example.aoli_core.wechat.template.AppRegisterTemplate;

@AppRegisterGenerator(
        packageName = "com.example.mishop",
        registerTemplate = AppRegisterTemplate.class)
public interface AppRegister {
}
