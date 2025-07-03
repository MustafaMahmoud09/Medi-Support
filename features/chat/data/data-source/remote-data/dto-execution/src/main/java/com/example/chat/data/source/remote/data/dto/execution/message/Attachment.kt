package com.example.chat.data.source.remote.data.dto.execution.message

import com.example.chat.domain.dto.declarations.message.IAttachment
import com.google.gson.annotations.SerializedName

data class Attachment(
    @SerializedName("new_name")
    override val newName: String,
    @SerializedName("old_name")
    override val oldName: String,
): IAttachment
