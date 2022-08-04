package com.compensation.portal.dataclasses.compensation

import com.compensation.portal.dataclasses.ValidityCheckable
import kotlin.math.sqrt

open class HistoricalData : ValidityCheckable {

    public var historicalMean: Float = 0f;
    public var historicalStdDev: Float = 0f;


    constructor() {
        this.historicalMean = 0f;
        this.historicalStdDev = 0f;
    }

    constructor(mean: Float, stdDev: Float) {
        this.historicalMean = mean;
        this.historicalStdDev = stdDev;
    }

    fun setHistoricalData (data: HistoricalData) {
        setHistoricalData(data.historicalMean, data.historicalStdDev);
    }

    fun setHistoricalData (mean: Float, stdDev: Float) {
        this.historicalMean = mean;
        this.historicalStdDev = stdDev;

    }

    fun extractHistoricalData(): HistoricalData = HistoricalData(historicalMean, historicalStdDev);


    operator fun plus(other: HistoricalData): HistoricalData {
        return HistoricalData(
            this.historicalMean + other.historicalMean,
            sqrt(this.historicalStdDev * this.historicalStdDev + other.historicalStdDev * other.historicalStdDev)
        )
    }

    override fun checkValidity(): Boolean {
        return (historicalMean != null) && (historicalStdDev != null);
    }

    override fun toString(): String {
        return "($historicalMean, $historicalStdDev)";
    }

}