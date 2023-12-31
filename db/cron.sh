#!/bin/bash
chmod +x ./batch-cleanup-script.sh
echo "*/1 * * * * /bin/bash /batch-cleanup-script.sh" | crontab -
