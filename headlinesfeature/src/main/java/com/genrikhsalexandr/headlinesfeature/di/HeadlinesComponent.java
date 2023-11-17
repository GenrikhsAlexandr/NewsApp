package com.genrikhsalexandr.headlinesfeature.di;

import com.genrikhsaleksandr.core.di.CoreDataModule;
import com.genrikhsaleksandr.core.di.NavigationModule;
import com.genrikhsalexandr.headlinesfeature.presentation.tabs.BusinessFragment;
import com.genrikhsalexandr.headlinesfeature.presentation.tabs.EntertainmentFragment;
import com.genrikhsalexandr.headlinesfeature.presentation.tabs.GeneralFragment;
import com.genrikhsalexandr.headlinesfeature.presentation.tabs.HealthFragment;
import com.genrikhsalexandr.headlinesfeature.presentation.tabs.ScienceFragment;
import com.genrikhsalexandr.headlinesfeature.presentation.tabs.SportFragment;
import com.genrikhsalexandr.headlinesfeature.presentation.tabs.TechnologyFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                HeadlinesModule.class,
                NavigationModule.class,
                CoreDataModule.class})
public interface HeadlinesComponent {
    void inject(BusinessFragment businessFragment);

    void inject(EntertainmentFragment entertainmentFragment);

    void inject(GeneralFragment generalFragment);

    void inject(HealthFragment healthFragment);

    void inject(ScienceFragment scienceFragment);

    void inject(SportFragment sportFragment);

    void inject(TechnologyFragment technologyFragment);
}