import unittest

class PrimeTestCase(unittest.TestCase):
    def test true if is prime (self):
        # Test true for all valid primes between 1 and 20
        primes = [2, 3, 5, 7, 11, 13, 17, 19]
        for prime in primes:
            self.assertTrue(is_prime(prime))
    
    def test_false_if_not_prime(self):
        # Test false for all valid non-primes between 1 and 20
        non_primes = [1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20]
        for non prime in non primes:
            self.assertFalse(is_ prime (non prime))

    def is prime(number):
        if number < 2:
            return False
        for i in range(2, number):
            if number % i == 0:
                return False
        return True

if __name__ == '__main__'
    unittest.main()
