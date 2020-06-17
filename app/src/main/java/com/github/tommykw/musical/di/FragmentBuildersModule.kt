package com.github.tommykw.musical.di

import com.github.tommykw.musical.ui.episodes.EpisodesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeThemeFragment(): EpisodesFragment
}