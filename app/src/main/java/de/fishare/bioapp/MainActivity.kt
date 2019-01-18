package de.fishare.bioapp

import android.app.Activity
import android.os.Bundle
import de.fishare.lumosble.CentralManager

class MainActivity : Activity() {
    private val centralMgr by lazy { CentralManager.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        centralMgr.checkPermit(this)
    }
}
