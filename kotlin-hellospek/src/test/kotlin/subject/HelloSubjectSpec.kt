package subject

import org.assertj.core.api.KotlinAssertions
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek

object HelloSubjectSpec : SubjectSpek<MutableList<String>>({

    subject {
        println("create subject")
        mutableListOf()
    }

    describe("describe spec") {
        action("adding") {

            it("empty") {
                KotlinAssertions.assertThat(subject).isEmpty()
            }

            it("can add") {
                subject.add("hello")
            }

            it("add add another") {
                subject.add("two")
            }

            it("not empty") {
                KotlinAssertions.assertThat(subject).isNotEmpty
            }
        }
    }

    describe("describe spec 2") {
        action("adding") {
            it("empty") {
                KotlinAssertions.assertThat(subject).isEmpty()
            }

            it("can add") {
                subject.add("hello")
            }

            it("add add another") {
                subject.add("two")
            }
        }
    }
})
