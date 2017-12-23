package com.github.nguyentrucxinh.foodmenulist.service.impl;

import com.github.nguyentrucxinh.foodmenulist.service.TemplateService;
import freemarker.template.Configuration;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.io.Files.getFileExtension;

@Service
public class FreemarkerTemplateServiceImpl implements TemplateService {

    private static final Logger LOGGER = Logger.getLogger(GoogleCloudStorageServiceImpl.class.getName());

    @Autowired
    private Configuration freemarkerConfiguration;

    @Override
    @NonNull
    public String mergeTemplateIntoString(final @NonNull String templateReference,
                                          final @NonNull Map<String, Object> model)
            throws IOException {
        final String trimmedTemplateReference = templateReference.trim();
        checkArgument(!isNullOrEmpty(trimmedTemplateReference), "The given template is null, empty or blank");
        if (trimmedTemplateReference.contains("."))
            checkArgument(Objects.equals(getFileExtension(trimmedTemplateReference), expectedTemplateExtension()),
                    "Expected a Freemarker template file with extension 'ftl', while '%s' was given",
                    getFileExtension(trimmedTemplateReference));

        try {
            final String normalizedTemplateReference = trimmedTemplateReference.endsWith(expectedTemplateExtension()) ?
                    trimmedTemplateReference : trimmedTemplateReference + '.' + expectedTemplateExtension();
            return FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate(normalizedTemplateReference, Charset.forName("UTF-8").name()), model);
        } catch (Exception e) {
            LOGGER.info("Throw Exception: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return null;
        }
    }

    @Override
    public String expectedTemplateExtension() {
        return "ftl";
    }

}
