import Button from './Button';
import { ChevronLeftIcon, ChevronRightIcon } from '../Icons';

function Pagination({
  pagePerView,
  activeColor,
  postTotalLength,
  paginationValue,
  onPaginationButtonClickHandler,
}) {
  return (
    <div className="flex items-center gap-6">
      <ChevronLeftIcon className="relative top-[1px] w-3 h-3" />
      {Array(parseInt(postTotalLength / pagePerView, 10) + 1)
        .fill(0)
        .map((_, index) => (
          <Button
            onClick={() => onPaginationButtonClickHandler(index + 1)}
            // eslint-disable-next-line react/no-array-index-key
            key={index}
            value={index + 1}
            className={paginationValue === index + 1 ? activeColor : 'text-inherit'}
          >
            {index + 1}
          </Button>
        ))}

      <ChevronRightIcon className="relative top-[1px] w-3 h-3" />
    </div>
  );
}

export default Pagination;
