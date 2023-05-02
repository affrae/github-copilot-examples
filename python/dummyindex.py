def smallest(l):
  """ find the 3 smallest elements in a list """
    return sorted(l)[:3]



def smallest(l,n):
  """ find the n smallest elements in a list without using sort """
  if n == 0:
    return []
  if len(l) == 0:
    return []
  if len(l) == 1:
    return l
  if n == 1:
    return [min(l)]
  else:
    return [min(l)] + smallest([x for x in l if x != min(l)],n-1)

# the function smallest(l,n) performs the following:
# 1. if n is 0, return an empty list
# 2. if the length of l is 0, return an empty list
# 3. if the length of l is 1, return l
# 4. if n is 1, return a list containing the minimum value of l
# 5. otherwise, return a list containing the smallest element in l
#    and the n-1 smallest elements in the list l without the smallest
#    element

# the function smallest(l,n) has a time complexity of 0(n^2) and a space complexity of 0(n)
# because it has to iterate through the list n times and create a new list of size n









class TestSmallest(unittest.TestCase):
  # test smallest(l,n) with random, dynamically generated integers
  def test_smallest(self):
    for i in range(100):
      l = random.randint(range(1000),random.randint(1,100))
      n = random.randint(1,len(l))
      theSmallest = smallest(l,n)
      self.assertEqual(theSmallest,sorted(l)[:n])

class TestSmallest(unittest.TestCase):
  # test smallest(l,n) with random, dynamically generated integers
  # do not allow duplicates in the list
  def test_smallest(self):
    for i in range(100):
      l = random.sample(range(1000),random.randint(1,100))
      n = random.randint(1,len(l))
      theSmallest = smallest(l,n)
      self.assertEqual(theSmallest,sorted(l)[:n])
      
unittest.main()         
