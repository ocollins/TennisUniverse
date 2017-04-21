<table class="add_table" id="add_table">
    <tr><td>Member ID</td>
        <td><input type="text" name="searchID" id="searchID" value="${aPerson.personId}" readonly="readonly"></td>
    </tr>
    <tr><td>First Name</td>
        <td><input type="text" name="fname" id="fName" value="${aPerson.firstName}" required></td>
    </tr>
    <tr><td>Last Name</td>
        <td><input type="text" name="lname" id="lName" value="${aPerson.lastName}" required></td>
    </tr>
    <tr><td>Social Security Number</td>
        <td><input type="text" name="ssn" id="ssn" value="${aPerson.ssnNr}" required></td>
    </tr>
    <tr><td>Birth Date</td>
        <td><input type="date" name="birth_date" id="birth_date" value="${aPerson.birthDt}" required></td>
    </tr>
    <tr><td>Street address 1</td>
        <td><input type="text" name="address_line1" id="address_line1" value="${aPerson.addressLine1}" required></td>
    </tr>
    <tr><td>Street address 2</td>
        <td><input type="text" name="address_line2" id="address_line2" value="${aPerson.addressLine2}"></td>
    </tr>
    <tr><td>City</td>
        <td><input type="text" name="city" id="city" value="${aPerson.city}" required></td>
    </tr>
    <tr><td>State</td>
        <td><input type="text" name="state" id="state" value="${aPerson.state}" required></td>
    </tr>
    <tr><td>Zip Code</td>
        <td><input type="text" name="zip" id="zip" value="${aPerson.zip}" required></td>
    </tr>
    <tr><td>Email Address</td>
        <td><input type="text" name="email" id="email" value="${aPerson.emailAddr}" required></td>
    </tr>
    <tr><td>Phone Number (no dashes - 9999999999)</td>
        <td><input type="text" name="phone" id="phone_input" value="${aPerson.phone}" required></td>
    </tr>
    </tr>
</table>