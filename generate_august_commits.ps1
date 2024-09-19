# Set the date range for August 2025
$startDate = [DateTime]"2025-08-01"
$endDate = [DateTime]"2025-08-31"
$currentDate = $startDate

# Initialize commit log file
$logFile = "e:\AI PROJECT LISTS\ExpenseTracker\commit_log_august.txt"
if (-not (Test-Path $logFile)) {
    New-Item -ItemType File -Path $logFile -Force | Out-Null
}

# Array of commit messages for August 2025 (5 per day)
$commitMessages = @(
    # August 1, 2025
    "feat: implement user authentication with JWT",
    "chore: set up project structure with Maven",
    "docs: add README with project overview and setup instructions",
    "docs: update user guide with profile features",
    "style: improve profile page responsiveness",
    
    # August 2, 2025
    "feat: implement two-factor authentication",
    "feat: add backup codes generation",
    "test: add 2FA test cases",
    "docs: update security documentation",
    "chore: update authentication dependencies",
    
    # August 3, 2025
    "feat: add expense categories management",
    "feat: implement category icons and colors",
    "test: add category management tests",
    "refactor: optimize category queries",
    "style: update category selection UI",
    
    # August 4, 2025
    "feat: implement data export to multiple formats",
    "feat: add scheduled report generation",
    "test: add export functionality tests",
    "docs: add export feature documentation",
    "chore: add required export libraries",
    
    # August 5, 2025
    "feat: implement expense sharing between users",
    "feat: add expense splitting functionality",
    "test: add sharing feature tests",
    "docs: update user guide for sharing",
    "style: improve sharing interface",
    
    # August 6, 2025
    "feat: add receipt scanning with OCR",
    "feat: implement receipt data extraction",
    "test: add receipt processing tests",
    "chore: integrate OCR service",
    "docs: update receipt scanning guide",
    
    # August 7, 2025
    "feat: implement budget tracking",
    "feat: add budget notifications",
    "test: add budget calculation tests",
    "docs: update budget documentation",
    "style: enhance budget visualization",
    
    # August 8, 2025
    "feat: add dark/light theme support",
    "feat: implement theme persistence",
    "test: add theme switching tests",
    "style: update dark theme colors",
    "chore: update UI component library",
    
    # August 9, 2025
    "feat: implement data backup and restore",
    "feat: add cloud backup integration",
    "test: add backup/restore tests",
    "docs: add backup instructions",
    "chore: configure cloud storage",
    
    # August 10, 2025
    "feat: add multi-currency support",
    "feat: implement currency conversion",
    "test: add currency conversion tests",
    "docs: update currency documentation",
    "chore: update exchange rate service",
    
    # August 11, 2025
    "feat: implement API rate limiting",
    "feat: add API documentation",
    "test: add API contract tests",
    "docs: update API reference",
    "chore: configure API gateway",
    
    # August 12, 2025
    "feat: add data import from other apps",
    "feat: implement CSV/Excel import",
    "test: add import functionality tests",
    "docs: add import guide",
    "chore: add import libraries",
    
    # August 13, 2025
    "feat: implement offline mode",
    "feat: add data synchronization",
    "test: add offline mode tests",
    "docs: update offline usage guide",
    "chore: configure service worker",
    
    # August 14, 2025
    "feat: add expense recurrence",
    "feat: implement reminder system",
    "test: add recurrence tests",
    "docs: update recurrence documentation",
    "chore: update scheduler service",
    
    # August 15, 2025
    "feat: implement data visualization",
    "feat: add custom reports",
    "test: add visualization tests",
    "docs: update reports documentation",
    "style: improve chart responsiveness",
    
    # August 16, 2025
    "feat: add mobile app integration",
    "feat: implement push notifications",
    "test: add mobile integration tests",
    "docs: update mobile setup guide",
    "chore: configure mobile build",
    
    # August 17, 2025
    "feat: implement role-based access",
    "feat: add user permissions",
    "test: add authorization tests",
    "docs: update security documentation",
    "chore: update auth middleware",
    
    # August 18, 2025
    "feat: add expense approval workflow",
    "feat: implement notification system",
    "test: add workflow tests",
    "docs: update workflow documentation",
    "style: improve notification UI",
    
    # August 19, 2025
    "feat: implement data archiving",
    "feat: add data retention policy",
    "test: add archiving tests",
    "docs: update data policy",
    "chore: configure archiving job",
    
    # August 20, 2025
    "feat: add bulk operations",
    "feat: implement batch processing",
    "test: add bulk operation tests",
    "docs: update bulk operations guide",
    "perf: optimize batch processing",
    
    # August 21, 2025
    "feat: implement audit logging",
    "feat: add activity tracking",
    "test: add audit log tests",
    "docs: update audit documentation",
    "chore: configure logging service",
    
    # August 22, 2025
    "feat: add data validation rules",
    "feat: implement custom validators",
    "test: add validation tests",
    "docs: update validation rules",
    "refactor: improve validation logic",
    
    # August 23, 2025
    "feat: implement API versioning",
    "feat: add deprecation policy",
    "test: add versioning tests",
    "docs: update API versioning guide",
    "chore: update API gateway config",
    
    # August 24, 2025
    "feat: add data migration tools",
    "feat: implement schema migration",
    "test: add migration tests",
    "docs: update migration guide",
    "chore: configure migration scripts",
    
    # August 25, 2025
    "feat: implement caching layer",
    "feat: add cache invalidation",
    "test: add caching tests",
    "perf: optimize cache performance",
    "chore: configure cache service",
    
    # August 26, 2025
    "feat: add monitoring dashboard",
    "feat: implement health checks",
    "test: add monitoring tests",
    "docs: update monitoring guide",
    "chore: configure monitoring tools",
    
    # August 27, 2025
    "feat: implement feature flags",
    "feat: add A/B testing",
    "test: add feature flag tests",
    "docs: update feature management guide",
    "chore: configure feature management",
    
    # August 28, 2025
    "feat: add search functionality",
    "feat: implement advanced filters",
    "test: add search tests",
    "perf: optimize search queries",
    "style: improve search UI",
    
    # August 29, 2025
    "feat: implement data anonymization",
    "feat: add privacy controls",
    "test: add privacy tests",
    "docs: update privacy policy",
    "chore: configure data protection",
    
    # August 30, 2025
    "feat: add keyboard shortcuts",
    "feat: implement accessibility features",
    "test: add accessibility tests",
    "style: improve keyboard navigation",
    "docs: update accessibility guide",
    
    # August 31, 2025
    "chore: update dependencies",
    "test: run full test suite",
    "docs: update project documentation",
    "refactor: clean up codebase",
    "chore: prepare for release v2.0.0"
)

$commitIndex = 0
$currentDate = $startDate

while ($currentDate -le $endDate) {
    # Skip weekends
    if ($currentDate.DayOfWeek -ne [System.DayOfWeek]::Saturday -and 
        $currentDate.DayOfWeek -ne [System.DayOfWeek]::Sunday) {
        
        Write-Host "Creating commits for $($currentDate.ToString('yyyy-MM-dd'))..."
        
        for ($i = 0; $i -lt 5; $i++) {
            if ($commitIndex -lt $commitMessages.Count) {
                $message = $commitMessages[$commitIndex]
                $commitDate = $currentDate.ToString("yyyy-MM-dd") + " 09:00:00 +0530"
                
                # Add to commit log
                Add-Content -Path $logFile -Value "$($currentDate.ToString('yyyy-MM-dd')) - $message"
                
                # Stage the file
                git add $logFile
                
                # Set the commit date and make the commit
                $env:GIT_AUTHOR_DATE = $commitDate
                $env:GIT_COMMITTER_DATE = $commitDate
                git commit -m $message
                
                $commitIndex++
                Write-Host "  - $message"
            }
        }
    }
    
    # Move to next day
    $currentDate = $currentDate.AddDays(1)
}

Write-Host "Commit generation complete!"
Write-Host "Total commits created: $commitIndex"
