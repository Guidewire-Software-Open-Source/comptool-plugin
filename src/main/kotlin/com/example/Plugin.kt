package com.example

import com.compensation.portal.plugins.CompensationPlugin
import com.compensation.portal.plugins.PluginFieldTypes

class Plugin : CompensationPlugin {

    var s: Int = 0;
    var backendVariable: Float = 0f;

    constructor() : super() {
        s = 0;
        fieldTypes["s"] = PluginFieldTypes.INTEGER;
        fieldTypes["Frontend Name"] = PluginFieldTypes.FLOAT_ARRAY
    };

    override fun setField(fieldName: String, value: Any) {
        if (fieldName == "s" && value is Int) {
            s = value;
        }

        if (fieldName == "Frontend Name" && value is FloatArray) {
            backendVariable = if (value.size > 0) value[0] else 0f;
        }
    }

    override fun getField(fieldName: String): Any {
        if (fieldName == "s")
            return s;
        if (fieldName == "Frontend Name")
            return listOf(backendVariable);

        return -1;
    }

    override fun calculateCompensation(year: Int): Float {
        return s.toFloat() * year;
    }

}