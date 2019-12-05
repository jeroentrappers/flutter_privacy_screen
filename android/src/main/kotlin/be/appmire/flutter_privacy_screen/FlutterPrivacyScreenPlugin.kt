package be.appmire.flutter_privacy_screen

import android.app.Activity
import android.view.WindowManager

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterPrivacyScreenPlugin(private val context: Activity): MethodCallHandler {

  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      if (null == registrar.activity()) {
        return;
      }
      val channel = MethodChannel(registrar.messenger(), "flutter_privacy_screen")
      channel.setMethodCallHandler(FlutterPrivacyScreenPlugin(registrar.activity()))
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if( call.method.equals("enablePrivacyScreen")) {
      context.window.setFlags(WindowManager.LayoutParams.FLAG_SECURE,
              WindowManager.LayoutParams.FLAG_SECURE)
      result.success(null)
    }
    else if( call.method.equals("disablePrivacyScreen")) {
      context.window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
      result.success(null)
    }
    else {
      result.notImplemented()
    }
  }
}
