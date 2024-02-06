// Function to create a new contact
function createContact() {
    var name = document.getElementById('name').value;
    var phoneNumber = document.getElementById('phoneNumber').value;
    var email = document.getElementById('email').value;

    if (!name || !phoneNumber || !email) {
        alert('Please fill in all fields.');
        return;
    }

    var contact = {
        name: name,
        phoneNumber: phoneNumber,
        email: email
    };

    fetch('http://localhost:8082/createContact', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(contact),
    })
    .then(response => response.json())
    .then(data => {
        alert('Contact created successfully!\nServer Response: ' + JSON.stringify(data));
        document.getElementById('contact'.reset()) ;
        
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while creating a contact. Please try again.');
    });
}

// Function to fetch all contacts
function getAllContacts() {
    fetch('http://localhost:8082/getContacts')
    .then(response => response.json())
    .then(contacts => {
        alert('List of Contacts : \n'+JSON.stringify(contacts)) ;
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while fetching contacts. Please try again.');
    });
}

// Function to search contacts by name
function searchContactByName() {
    var searchName = document.getElementById('searchName').value;

    if (!searchName) {
        alert('Please enter a name to search.');
        return;
    }

    fetch('http://localhost:8082/getContact?name=' + encodeURIComponent(searchName))
        .then(response => response.json())
        .then(foundContacts => {
            if(foundContacts!=null){
            alert('Found Contact : \n'+JSON.stringify(foundContacts)) ;
            } else {
                alert('No Student found with given name.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while searching for contacts. Please try again.');
        });
}

