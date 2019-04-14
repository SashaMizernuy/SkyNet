package ua.genesis.sasha.skynet;



import javax.inject.Singleton;

import dagger.Component;



@Singleton
@Component(modules={ ApiModule.class})
public interface MyComponent {

    void inject(MainActivity mainActivity);
    void inject(DetailsFragments detailsFragments);
}
