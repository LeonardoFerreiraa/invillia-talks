package br.com.leonardoferreira.future.executionlistener

import br.com.leonardoferreira.future.base.CleanDatabase
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.test.context.TestContext
import org.springframework.test.context.support.AbstractTestExecutionListener

class CleanDatabaseTestExecutionListener : AbstractTestExecutionListener() {

    override fun beforeTestMethod(testContext: TestContext) {
        try {
            testContext.applicationContext
                .getBean(CleanDatabase::class.java)
                .clean()
        } catch (e: NoSuchBeanDefinitionException) {
            println(e.message)
        }
    }

}
