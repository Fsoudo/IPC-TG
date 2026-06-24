// Ficheiro de configuracao "geral" do projeto.
// As versoes dos plugins estao definidas no ficheiro gradle/libs.versions.toml
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
