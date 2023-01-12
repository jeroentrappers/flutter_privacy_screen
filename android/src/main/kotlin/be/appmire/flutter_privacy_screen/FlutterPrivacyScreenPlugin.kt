package be.appmire.flutter_privacy_screen

import android.view.WindowManager
import android.content.Context
import android.app.Activity

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding

class FlutterPrivacyScreenPlugin(): FlutterPlugin, MethodCallHandler, ActivityAware {

  private lateinit var context: Context
  private lateinit var channel: MethodChannel
  private var activity: Activity? = null

  override fun onAttachedToEngine(binding: FlutterPluginBinding) {
    channel = MethodChannel(binding.binaryMessenger, "flutter_privacy_screen")
    context = binding.applicationContext
    channel.setMethodCallHandler(this)
  }

  override fun onDetachedFromEngine(binding: FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.getActivity()
  }

  override fun 		onDetachedFromActivity() {
  activity =  null;
  }
  override fun 		onDetachedFromActivityForConfigChanges() {
    activity = null;
    }
  override fun 		onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding){
    activity = binding.getActivity()
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if( call.method.equals("enablePrivacyScreen")) {
      activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_SECURE,
              WindowManager.LayoutParams.FLAG_SECURE)
      result.success(null)
    }
    else if( call.method.equals("disablePrivacyScreen")) {
      activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
      result.success(null)
    }
    else {
      result.notImplemented()
    }
  }
}
