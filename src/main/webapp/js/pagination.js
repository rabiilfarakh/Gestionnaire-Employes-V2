// pagination.js

// Get job elements and page numbers container
const jobs = document.querySelectorAll('.job');
const pageNumbersContainer = document.getElementById('page-numbers');

let currentPage = 1;
const jobsPerPage = 6;
const totalPages = Math.ceil(jobs.length / jobsPerPage);

// Function to display jobs based on the current page
function displayJobs(page) {
    // Hide all jobs
    jobs.forEach(job => job.style.display = 'none');

    // Calculate start and end indices for the jobs to display
    const start = (page - 1) * jobsPerPage;
    const end = start + jobsPerPage;

    // Show jobs for the current page
    jobs.forEach((job, index) => {
        if (index >= start && index < end) {
            job.style.display = 'block';
        }
    });

    // Update pagination buttons
    updatePagination(page);
}

// Function to generate pagination buttons
function updatePagination(page) {
    // Clear existing pagination buttons
    pageNumbersContainer.innerHTML = '';

    // Generate new pagination buttons
    for (let i = 1; i <= totalPages; i++) {
        const pageNumberBtn = document.createElement('button');
        pageNumberBtn.textContent = i;
        if (i === page) {
            pageNumberBtn.classList.add('active');
        }
        pageNumberBtn.addEventListener('click', () => {
            currentPage = i;
            displayJobs(currentPage);
        });
        pageNumbersContainer.appendChild(pageNumberBtn);
    }
}

// Display the first page on load
displayJobs(currentPage);
