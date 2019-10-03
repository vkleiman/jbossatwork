<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="/css/default.css">
  
  <script language="JavaScript">
      function checkAll(field)
      {
          for (i=0; i < field.length; i++)
          {
              field[i].checked = true;
          }
      }
  </script>
  
</head>

<body>

  <p><a href="carForm?id=0">[Add Car]</a></p>

  <form name="deleteForm" method="post" action="carDelete">
  <input type="hidden" name="action" value="deleteCar" />
  <table>
    <tr>
      <th><a href="javascript:checkAll(document.deleteForm.id)">Delete</a></th>
      <th>Action</th>
      <th>Make</th>
      <th>Model</th>
      <th class="model-year">Model Year</th>
      <th>Buy Car</th>
    </tr>
    
    <c:forEach items='${carList}' var='car'>
      <tr>
      <td><input type="checkbox" name="id" value="${car.id}"></td>
      <td><a href="carForm?id=${car.id}">Edit</a></td>
      <td>${car.make}</td>
      <td>${car.model}</td>
      <td class="model-year">${car.modelYear}</td>
      <td><a href="buyForm?id=${car.id}">Buy</a></td>
      </tr>
    </c:forEach>
    
    <tr>
      <td colspan="6">
        <input type="submit" name="Delete Checked" value="Delete Checked" />
        &nbsp;&nbsp;
        <input type="reset" name="Reset" value="Reset" />
      </td>
    </tr>
    
  </table>
  </form>
</body>
</html>

