package com.cmesquita.realstation.fake

import com.cmesquita.realstation.utils.coroutines.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class FakeCoroutineDispatchers : AppCoroutineDispatchers {

    private val testCoroutineScheduler = TestCoroutineScheduler()

    override fun io(): CoroutineDispatcher = UnconfinedTestDispatcher(testCoroutineScheduler)

    override fun default(): CoroutineDispatcher = UnconfinedTestDispatcher(testCoroutineScheduler)
}
