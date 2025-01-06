document.getElementById('transferForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission

    const recipient = document.getElementById('recipient').value.trim();
    const amount = document.getElementById('amount').value.trim();

    // Here you would typically send this data to your server via AJAX
    alert(`Transferring $${amount} to ${recipient}. Feature coming soon!`);

    // Reset form fields
    this.reset();
});