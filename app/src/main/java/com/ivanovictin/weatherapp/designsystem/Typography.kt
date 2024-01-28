package com.ivanovictin.weatherapp.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.ivanovictin.weatherapp.R

private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val robotoFontName = GoogleFont("Roboto")
private val robotoFontFamily =
    FontFamily(Font(googleFont = robotoFontName, fontProvider = provider))

private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = robotoFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = robotoFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = robotoFontFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = robotoFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = robotoFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = robotoFontFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = robotoFontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = robotoFontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = robotoFontFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = robotoFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = robotoFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = robotoFontFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = robotoFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = robotoFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = robotoFontFamily)
)
