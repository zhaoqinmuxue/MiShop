package com.example.mishop.generators;

import com.example.aoli_annotations.PayEntryGenerator;
import com.example.aoli_core.wechat.template.WXPayEntryTemplate;

@PayEntryGenerator(
        packageName = "com.example.mishop",
        payEntryTemplate = WXPayEntryTemplate.class)
public interface WeChatPayEntry {
}
