package com.example.mishop.generators;

import com.example.aoli_annotations.EntryGenerator;
import com.example.aoli_core.wechat.template.WXEntryTemplate;

@EntryGenerator(
        packageName = "com.example.mishop",
        entryTemplate = WXEntryTemplate.class)
public interface WeChatEntry {
}
