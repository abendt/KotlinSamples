package data

import org.assertj.core.api.KotlinAssertions
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object HelloDataSpec: Spek( {

    describe("a calculator") {

        val data = arrayOf(
            data(4, 2, expected = 6),
            data(1, 3, expected = 4),
            data(5, 7, expected = 12)
        )

        on("addition %s and %s", with = *data) { input1, input2, expected ->

            test("returns $expected") {
                KotlinAssertions.assertThat(input1 + input2).isEqualTo(expected)
            }
        }
    }

})
