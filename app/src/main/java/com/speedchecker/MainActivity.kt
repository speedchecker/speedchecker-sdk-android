package com.speedchecker

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import com.speedchecker.android.sdk.Public.SpeedTestListener
import com.speedchecker.android.sdk.Public.SpeedTestResult
import com.speedchecker.android.sdk.SpeedcheckerSDK
import com.speedchecker.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), SpeedTestListener {

    private lateinit var binding: ActivityMainBinding
    private val sdf = SimpleDateFormat("HH:mm:ss.SSS", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLog.movementMethod = ScrollingMovementMethod()
        binding.testProgressBar.max = 240

        SpeedcheckerSDK.init(this)
        SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(this)

        binding.btnStart.setOnClickListener {
            SpeedcheckerSDK.SpeedTest.startTest(this@MainActivity)
            binding.btnStart.isEnabled = false
            binding.testProgressBar.progress = 0
            binding.containerResult.visibility = View.INVISIBLE
            binding.containerTest.visibility = View.VISIBLE
        }
    }

    override fun onTestStarted() {
        binding.tvProgress.text = ""
        binding.tvCurrentTestValue.text = ""
        binding.tvTrafficTestValue.text = ""
        binding.ivTestStatusIcon.setImageBitmap(null)
        binding.tvTestStatusText.text = getString(R.string.test_started)
        log(getString(R.string.test_started))
    }

    override fun onFetchServerFailed(errorCode: Int) {
        binding.tvTestStatusText.text = getString(R.string.text_servers_error)
        log(getString(R.string.text_servers_error))
        binding.btnStart.isEnabled = true
    }

    override fun onFindingBestServerStarted() {
        binding.tvTestStatusText.text = getString(R.string.test_fetch_server_progress)
        log(getString(R.string.test_fetch_server_progress))
    }

    override fun onTestFinished(speedTestResult: SpeedTestResult) {
        binding.btnStart.isEnabled = true
        binding.containerTest.visibility = View.INVISIBLE
        binding.containerResult.visibility = View.VISIBLE
        binding.tvServer.text = speedTestResult.server.Domain
        val pingSpan = SpannableStringBuilder()
        pingSpan.bold { append(speedTestResult.ping.toString()) }.append(" ms")
        binding.tvResultPing.text = pingSpan
        binding.tvTestResultDownload.text = "${speedTestResult.downloadSpeed}"
        binding.tvTestResultUpload.text = "${speedTestResult.uploadSpeed}"
        val jitterSpan = SpannableStringBuilder()
        jitterSpan.bold { append(speedTestResult.jitter.toString()) }.append(" ms")
        binding.tvResultJitter.text = jitterSpan
        val indoorStatus =
            if (speedTestResult.indoorOutdoorStatus == 0) getString(R.string.status_outdoor) else getString(
                R.string.status_indoor
            )
        binding.tvLocationType.text = indoorStatus
        speedTestResult.packetLoss?.let {
            binding.tvResultPacketLoss.text = speedTestResult.packetLoss.toString() + "%"
        } ?: run {
            binding.tvResultPacketLoss.text = "-"
        }
        binding.ivTestStatusIcon.setImageBitmap(null)
        binding.tvConnectionType.text = speedTestResult.connectionTypeHuman
        binding.tvTestStatusText.text = getString(R.string.test_finished)
        log("Test Finished: Server[" + speedTestResult.server.Domain + "] -> " + speedTestResult.toString())
    }

    override fun onPingStarted() {
        binding.testProgressBar.setProgress(20, true)
        binding.tvTestStatusText.text = getString(R.string.common_ping)
        binding.ivTestStatusIcon.setImageResource(R.drawable.ic_ping)
        binding.tvProgress.text = getString(R.string.common_progress)
        log("Ping Started")
    }

    override fun onPingFinished(ping: Int, jitter: Int) {
        binding.testProgressBar.setProgress(40, true)
        binding.tvTestStatusText.text = getString(R.string.test_ping_finished)
        binding.tvCurrentTestValue.text = "$ping ms | jitter: $jitter"
        log("Ping Finished: $ping ms| jitter: $jitter")
    }

    override fun onDownloadTestStarted() {
        binding.tvTestStatusText.text = getString(R.string.test_download_started)
        binding.ivTestStatusIcon.setImageResource(R.drawable.ic_download)
        log("Download Test Started")
    }

    override fun onDownloadTestProgress(progress: Int, speedMbs: Double, transferredMb: Double) {
        binding.testProgressBar.setProgress(40 + progress, true)
        binding.tvTestStatusText.text = getString(R.string.common_download)
        binding.ivTestStatusIcon.setImageResource(R.drawable.ic_download)
        binding.tvProgress.text = "$progress%"
        binding.tvCurrentTestValue.text = "$speedMbs Mb/s"
        binding.tvTrafficTestValue.text = "TransferredMb: $transferredMb"
        log("Download Test Progress: $progress% -> $speedMbs Mb/s\nTransferredMb: $transferredMb")
    }

    override fun onDownloadTestFinished(v: Double) {
        binding.tvTestStatusText.text = getString(R.string.test_download_finished)
        binding.tvCurrentTestValue.text = "$v Mb/s"
        log("Download Test Finished: $v Mb/s")
    }

    override fun onUploadTestStarted() {
        binding.tvTestStatusText.text = getString(R.string.common_upload)
        binding.ivTestStatusIcon.setImageResource(R.drawable.ic_upload)
        binding.tvProgress.text = ""
        binding.tvCurrentTestValue.text = ""
        binding.tvTrafficTestValue.text = ""
        log("Upload Test Started")
    }

    override fun onUploadTestProgress(progress: Int, speedMbs: Double, transferredMb: Double) {
        binding.testProgressBar.setProgress(140 + progress, true)
        binding.tvProgress.text = "$progress%"
        binding.tvCurrentTestValue.text = "$speedMbs Mb/s"
        binding.tvTrafficTestValue.text = "TransferredMb: $transferredMb"
        log("Upload Test Progress: $progress% -> $speedMbs Mb/s\nTransferredMb: $transferredMb")
    }

    override fun onUploadTestFinished(v: Double) {
        binding.tvCurrentTestValue.text = "$v Mb/s"
        log("Upload Test Finished: $v Mb/s")
    }

    override fun onTestWarning(s: String?) {
        binding.tvCurrentTestValue.text = getString(R.string.test_warning)
        binding.tvTrafficTestValue.text = s
        log("Test Warning: $s")
    }

    override fun onTestFatalError(s: String?) {
        binding.tvCurrentTestValue.text = getString(R.string.test_error)
        binding.tvTrafficTestValue.text = s
        binding.btnStart.isEnabled = true
        log("Test Fatal Error: $s")
    }

    override fun onTestInterrupted(s: String?) {
        binding.tvTestStatusText.text = getString(R.string.test_interrupted)
        binding.tvCurrentTestValue.text = s
        binding.btnStart.isEnabled = true
    }

    private fun log(message: String) {
        binding.tvLog.text = ("[" + sdf.format(Date(System.currentTimeMillis())) + "]: "
                + message + "\n" + binding.tvLog.text)
    }

}