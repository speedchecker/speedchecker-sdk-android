package com.speedchecker.android.sdk.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BackgroundPermissionDialog : DialogFragment() {

    private lateinit var doOnConfirm: (() -> Unit)

    companion object {
        fun show(activity: AppCompatActivity, confirm: () -> Unit) {
            val helpUsDialog = BackgroundPermissionDialog()
            helpUsDialog.doOnConfirm = confirm
            helpUsDialog.show(
                activity.supportFragmentManager,
                BackgroundPermissionDialog::class.java.simpleName
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v: View = inflater.inflate(R.layout.dialog_background_permission, null)
        v.findViewById<View>(R.id.btnNext).setOnClickListener {
            dismiss()
            doOnConfirm.invoke()
        }
        val allowAllRadioButton = v.findViewById<RadioButton>(R.id.rbAllowAll)
        lifecycleScope.launch {
            delay(1000)
            touchAction(allowAllRadioButton, MotionEvent.ACTION_DOWN)
            delay(300)
            touchAction(allowAllRadioButton, MotionEvent.ACTION_UP)
        }
        return v
    }

    private fun touchAction(view: View, motionEvent: Int) {
        val event: MotionEvent = MotionEvent.obtain(
            System.currentTimeMillis(),
            System.currentTimeMillis(),
            motionEvent,
            view.pivotX,
            view.pivotY,
            0
        )
        view.dispatchTouchEvent(event)
    }
}