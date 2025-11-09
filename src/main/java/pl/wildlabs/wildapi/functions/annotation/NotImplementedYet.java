package pl.wildlabs.wildapi.functions.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS) // Adnotacja widoczna w trakcie kompilacji, ale nie w czasie działania
@Target(ElementType.METHOD)       // Można stosować tylko dla metod
@Deprecated                      // Dodanie tej adnotacji zapewni przekreślenie
public @interface NotImplementedYet {
    String message() default "This function is not implemented yet";
}
