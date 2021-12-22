package com.kc.intern.project.freemarker

import com.kc.intern.project.freemarker.model.Screen
import freemarker.template.*
import java.io.File
import java.io.IOException
import java.io.StringWriter

class FreemarkerUtils {

    private val cfg: Configuration = Configuration()
    private val fileTemplates = "src/main/kotlin/com/kc/intern/project/freemarker/templates"
    val fileOut = "src/main/kotlin/com/kc/intern/project/freemarker/templates/output.html"

    @Throws(TemplateException::class, IOException::class)
    fun parseTemplate(obj: Screen, templateName: String?): String {

        cfg.setDirectoryForTemplateLoading(File(fileTemplates))
        cfg.objectWrapper = DefaultObjectWrapper()
        val t: Template = cfg.getTemplate(templateName)
        val writer = StringWriter()
        t.process(obj, writer)
        writer.flush()
        writer.close()
        return writer.toString()
    }

}