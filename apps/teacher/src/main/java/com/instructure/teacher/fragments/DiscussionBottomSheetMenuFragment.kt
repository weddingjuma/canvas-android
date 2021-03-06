/*
 * Copyright (C) 2020 - present Instructure, Inc.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, version 3 of the License.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.instructure.teacher.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.instructure.pandautils.utils.dismissExisting
import com.instructure.teacher.R
import com.instructure.teacher.events.DiscussionOverflowMenuClickedEvent
import kotlinx.android.synthetic.main.bottom_sheet_discussion_menu.view.*
import org.greenrobot.eventbus.EventBus

class DiscussionBottomSheetMenuFragment : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_discussion_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.markAsUnread.setOnClickListener {
            EventBus.getDefault().post(DiscussionOverflowMenuClickedEvent(DiscussionBottomSheetChoice.MARK_AS_UNREAD, entryId))
            this.dismiss()
        }

        view.edit.setOnClickListener {
            EventBus.getDefault().post(DiscussionOverflowMenuClickedEvent(DiscussionBottomSheetChoice.EDIT, entryId))
            this.dismiss()
        }

        view.delete.setOnClickListener {
            EventBus.getDefault().post(DiscussionOverflowMenuClickedEvent(DiscussionBottomSheetChoice.DELETE, entryId))
            this.dismiss()
        }
    }

    companion object {
        var entryId: Long = -1

        fun newInstance(): DiscussionBottomSheetMenuFragment = DiscussionBottomSheetMenuFragment()

        @JvmStatic
        fun show(manager: FragmentManager, id: Long) {
            entryId = id
            manager.dismissExisting<DiscussionBottomSheetMenuFragment>()
            val dialog = newInstance()
            dialog.show(manager, DiscussionBottomSheetMenuFragment::class.java.simpleName)
        }
    }
}

enum class DiscussionBottomSheetChoice {
    MARK_AS_UNREAD, EDIT, DELETE
}