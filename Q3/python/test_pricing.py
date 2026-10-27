import unittest

from pricing import price


class PriceTest(unittest.TestCase):
    # One test per equivalence class, plus both sides of every boundary.
    def test_below_minimum(self):
        self.assertEqual(21000, price(1.0, False, False))

    def test_exactly_minimum(self):
        self.assertEqual(21000, price(3.0, False, False))

    def test_just_over_minimum(self):
        self.assertEqual(24500, price(3.01, False, False))

    def test_exactly_a_half(self):
        self.assertEqual(24500, price(3.5, False, False))

    def test_just_over_a_half(self):
        self.assertEqual(28000, price(3.51, False, False))

    def test_express(self):
        self.assertEqual(36750, price(3.5, True, False))

    def test_member(self):
        self.assertEqual(22050, price(3.5, False, True))

    def test_express_member(self):
        self.assertEqual(33075, price(3.5, True, True))

    def test_rejects_zero_and_negative(self):
        for kg in (0.0, -1.0):
            with self.assertRaises(ValueError):
                price(kg, False, False)


if __name__ == "__main__":
    unittest.main(verbosity=2)
