package im.fdx.v2ex.ui.member

import android.os.Parcelable
import im.fdx.v2ex.model.BaseModel
import im.fdx.v2ex.ui.main.Topic
import kotlinx.android.parcel.Parcelize

/**
 * Created by fdx on 2017/7/16.
 * fdx will maintain it
 */
@Parcelize
data class MemberReplyModel(var id: String? = "",
                            var topic: Topic = Topic(),
                            var content: String? = null,
                            var create: Long = 0L) : BaseModel(), Parcelable