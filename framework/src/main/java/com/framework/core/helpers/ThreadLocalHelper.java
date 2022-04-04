package com.framework.core.helpers;

import com.framework.core.global.Context;
import com.framework.core.results.FeatureResult;

public class ThreadLocalHelper {

    public static ThreadLocal<Context> context = new ThreadLocal<>();

    public static ThreadLocal<FeatureResult> featureResult = new ThreadLocal<>();
}
