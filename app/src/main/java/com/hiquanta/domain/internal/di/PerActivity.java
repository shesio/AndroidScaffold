package com.hiquanta.domain.internal.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by hiquanta on 2016/9/26.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {}
