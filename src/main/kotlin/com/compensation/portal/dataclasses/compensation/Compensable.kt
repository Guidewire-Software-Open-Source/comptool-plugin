package com.compensation.portal.dataclasses.compensation

interface Compensable {

    fun calculateCompensation(year: Int): Float


    //compensation from year 1 to year n
    fun calculateTotalCompensation(year: Int): Float {
        var total = 0f
        for (i in (0..year))
            total += calculateCompensation(i)

        return total
    }


    /**
     * Used to calculate total compensation between start and end years, both inclusive.
     */
    fun calculateCompensationBetween(startYear: Int, endYear: Int): Float {
        if (startYear <= 1) return calculateTotalCompensation(endYear)

        return calculateTotalCompensation(endYear) - calculateTotalCompensation(startYear - 1)
    }
}