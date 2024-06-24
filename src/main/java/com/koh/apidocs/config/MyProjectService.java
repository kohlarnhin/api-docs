package com.koh.apidocs.config;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurableProvider;
import com.intellij.openapi.options.ex.ConfigurableExtensionPointUtil;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Service(Service.Level.PROJECT)
public final class MyProjectService {
    public MyProjectService(@NotNull Project project) {

        ConfigurableExtensionPointUtil.createProjectConfigurableForProvider(project, ApidocsConfigurableProvider.class);
        ConfigurableExtensionPointUtil.createProjectConfigurableForProvider(project, MyConfigurableProvider.class);
    }

    private static class MyConfigurableProvider extends ConfigurableProvider {
        @Nullable
        @Override
        public Configurable createConfigurable() {
            return new MyConfigurable();
        }

        @Override
        public boolean canCreateConfigurable() {
            return true;
        }
    }

    private static class ApidocsConfigurableProvider extends ConfigurableProvider {
        @Nullable
        @Override
        public Configurable createConfigurable() {
            return new ApidocsConfigurable();
        }

        @Override
        public boolean canCreateConfigurable() {
            return true;
        }
    }
}







