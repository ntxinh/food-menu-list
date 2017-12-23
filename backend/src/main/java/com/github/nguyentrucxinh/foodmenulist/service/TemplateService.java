package com.github.nguyentrucxinh.foodmenulist.service;

import java.io.IOException;
import java.util.Map;

public interface TemplateService {

    /**
     * Call the templateReference engine to process the given templateReference with the given model object.
     *
     * @param templateReference a templateReference file to be processed. The name must be complaint with the position
     *                          of the template your your resources folder. Usually, files in {@code resources/templates}
     *                          are resolved by passing the sole file name. Subfolders of {@code resources/templates} must
     *                          be explicitly reported. E.g., a template {@code template.html} under the folder
     *                          {@code resources/templates/module} must be reported as {@code "module/template"}
     * @param model             the model object to process the templateReference
     * @return a processed template (an HTML, or XML, or whatever the templateReference engine can process)
     * @throws IOException thrown if the templateReference file is not found or cannot be accessed
     */
    String mergeTemplateIntoString(String templateReference, Map<String, Object> model)
            throws IOException;


    /**
     * Return the expected template file extension. The String must not start with '.'.
     *
     * @return The expected extension of thr accepted template file
     */
    String expectedTemplateExtension();
}
