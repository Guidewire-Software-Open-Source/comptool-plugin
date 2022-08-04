package com.compensation.portal.plugins

import com.compensation.portal.dataclasses.compensation.Compensable
import com.compensation.portal.dataclasses.compensation.HistoricalData

abstract class CompensationPlugin : HistoricalData, Compensable {
    var fieldTypes: HashMap<String, PluginFieldTypes> = HashMap();


    constructor() : super();

    abstract fun setField (fieldName: String, value: Any): Any

    abstract fun getField (fieldName: String): Any;

    operator fun set (fieldName: String, value: Any) = setField(fieldName, value);
    operator fun get (fieldName: String): Any = getField(fieldName);

}


enum class PluginFieldTypes {
    INTEGER, FLOAT, BOOLEAN, STRING, INTEGER_ARRAY, FLOAT_ARRAY, BOOLEAN_ARRAY, STRING_ARRAY;
}